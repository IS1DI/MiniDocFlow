package MiniDFlow.service;

import MiniDFlow.entity.Author;
import MiniDFlow.entity.Authority;
import MiniDFlow.repository.AuthorRepository;
import MiniDFlow.repository.AuthorityRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public void create(Author author, Collection<? extends GrantedAuthority> grantedAuthorities) throws DuplicateMemberException {
        if(authorRepository.getByUsername(author.getUsername()).isEmpty()){
            authorRepository.create(author);
            grantedAuthorities.stream().map(a -> new Authority(a.getAuthority(), author)).forEach(authority -> authorityRepository.create(authority));
        }
    }


    @Transactional
    public boolean exist(String s) {
        return authorRepository.exist(s);
    }

    @Transactional(rollbackOn = UsernameNotFoundException.class)
    public Author findByUsername(String s) {
        return authorRepository.getByUsername(s).orElseThrow(() -> new UsernameNotFoundException("not found"));
    }

    @Transactional(rollbackOn = UsernameNotFoundException.class)
    public void changePassword(String s, String s1) {
        Author author = authorRepository.getByUsername(s).orElseThrow(() -> new UsernameNotFoundException("not found"));
        author.setPassword(s1);
    }
    @Transactional(rollbackOn = UsernameNotFoundException.class)
    public void delete(String s) {
        authorRepository.deleteByEntity(authorRepository.getByUsername(s).orElseThrow(() -> new UsernameNotFoundException("not found")));
    }

    @Transactional(rollbackOn = UsernameNotFoundException.class)
    public void updateByUsername(String username, Author author) {
        Author authorBefore = authorRepository.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
        authorBefore.setAuthorities(author.getAuthorities());
        authorBefore.setPassword(author.getPassword());

    }
}
