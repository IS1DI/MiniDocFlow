package MiniDFlow.config;

import org.apache.lucene.analysis.charfilter.MappingCharFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.en.EnglishPossessiveFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.analyzer.definition.LuceneAnalysisDefinitionProvider;
import org.hibernate.search.analyzer.definition.LuceneAnalysisDefinitionRegistryBuilder;
import org.springframework.stereotype.Component;

@Component
public class LuceneAnalysisConfig implements LuceneAnalysisDefinitionProvider {

    @Override
    public void register(LuceneAnalysisDefinitionRegistryBuilder builder) {
        builder
                .analyzer( "docVerAnalyzer" )
                .tokenizer( StandardTokenizerFactory.class )
                .charFilter( MappingCharFilterFactory.class )
                .tokenFilter(EnglishPossessiveFilterFactory.class)
                .tokenFilter( ASCIIFoldingFilterFactory.class )
                .tokenFilter( LowerCaseFilterFactory.class )
                .tokenFilter( StopFilterFactory.class )
                .param( "ignoreCase", "true" );
    }
}