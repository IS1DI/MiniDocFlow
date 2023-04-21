package MiniDFlow.service;

import MiniDFlow.POJO.DocumentPOJO;
import MiniDFlow.POJO.DocumentUpdatePOJO;
import MiniDFlow.entity.Author;
import MiniDFlow.entity.Document;
import MiniDFlow.entity.DocumentVersion;
import MiniDFlow.entity.RegisterCard;
import MiniDFlow.entity.projection.DocumentView;
import MiniDFlow.repository.DocumentRepository;
import MiniDFlow.repository.DocumentVersionRepository;
import MiniDFlow.repository.RegisterCardRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private RegisterCardRepository registerCardRepository;
    @Autowired
    private DocumentVersionRepository documentVersionRepository;

    @Transactional
    public void createNewDocument(DocumentPOJO documentPOJO, Author author) throws IOException {
        Document document;
        String origDocName = documentPOJO.getFile().getOriginalFilename();
        String ext = FilenameUtils.getExtension(origDocName);
        String docName = documentPOJO.getName() + (ext!=null&&!ext.isBlank()?"."+ext:"");
        documentRepository.create(document = new Document(docName, author));
        registerCardRepository.create(new RegisterCard(document, documentPOJO.getDocumentIntroNumber(), Instant.now()));
        documentVersionRepository.create(new DocumentVersion(document, author, documentPOJO.getFile().getBytes()));
    }

    @Transactional
    public List<DocumentView> getAllExistDocViews() {
        List<Document> documents = documentRepository.getAllExistDocuments();
        return documents.stream().map(doc -> {
            DocumentVersion docVer = documentVersionRepository.getVersionByDocument(doc, doc.getLastVersion());
            return new DocumentView(
                    doc.getId(),
                    doc.getName(),
                    docVer.getVersionAuthor().getUsername(),
                    docVer.getContent().length,
                    docVer.getVersion(),
                    doc.isExist()
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public List<DocumentView> getAllDocViews() {
        return documentRepository.getAll().stream().map(doc -> {
            DocumentVersion docVer = documentVersionRepository.getVersionByDocument(doc, doc.getLastVersion());
            return new DocumentView(
                    doc.getId(),
                    doc.getName(),
                    docVer.getVersionAuthor().getUsername(),
                    docVer.getContent().length,
                    docVer.getVersion(),
                    doc.isExist()
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public void updateDocumentById(DocumentUpdatePOJO documentUpdatePOJO, Author author) throws IOException {
        Document document = documentRepository.getById(documentUpdatePOJO.getDocumentId());
        if (document.isExist()) {
            documentVersionRepository.update(new DocumentVersion(
                    document,
                    author,
                    documentUpdatePOJO.getFile().getBytes(),
                    document.getLastVersion() + 1)
            );
        } else {
            throw new RuntimeException("Document deleted");
        }
    }

    /**
     *
     * @param id document id
     * @return  List of DocumentView with all versions by document
     */
    @Transactional
    public List<DocumentView> getAllDocViewsById(int id) {
        Document doc = documentRepository.getById(id);
        return documentVersionRepository
                .getAllVerByDocument(doc)
                .stream().map(docVer ->
                        new DocumentView(
                                doc.getId(),
                                doc.getName(),
                                docVer.getVersionAuthor().getUsername(),
                                docVer.getContent().length,
                                docVer.getVersion(),
                                doc.isExist()
                        )).collect(Collectors.toList());
    }

    /**
     * if ver < 1: returns last version
     * @param id document id
     * @param ver version of document
     * @return DocumentView with version
     */
    @Transactional
    public DocumentView getDocById(int id, int ver){
        Document doc = documentRepository.getById(id);
        DocumentVersion docVer = documentVersionRepository.getVersionByDocument(doc,ver<1?doc.getLastVersion():ver);
        return new DocumentView(
                doc.getId(),
                doc.getName(),
                docVer.getVersionAuthor().getUsername(),
                docVer.getContent().length,
                docVer.getVersion(),
                doc.isExist()
        );
    }


    @Transactional
    public void deleteDocumentById(int id, String documentExternNumber) {
        registerCardRepository.update(documentRepository.getById(id), documentExternNumber);
    }

    @Transactional
    public byte[] getFile(int id,int ver){
        Document document = documentRepository.getById(id);
        return documentVersionRepository.getVersionByDocument(document,ver>=1?ver:document.getLastVersion()).getContent();
    }
}