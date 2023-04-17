package MiniDFlow.security;

import MiniDFlow.entity.Author;
import MiniDFlow.service.AuthorService;
import MiniDFlow.service.AuthoritiesService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class JdbcAuthorService implements UserDetailsManager {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthoritiesService authoritiesService;
    @Autowired
    PasswordEncoder encoder;


    @Override
    public void createUser(UserDetails userDetails) {
        Author author = new Author(userDetails.getUsername(), encoder.encode(userDetails.getPassword()));
        try {
            authorService.create(author,userDetails.getAuthorities());
        } catch (DuplicateMemberException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        Author author = new Author(userDetails.getUsername(),encoder.encode(userDetails.getPassword()));
        authorService.updateByUsername(userDetails.getUsername(),author);
        authoritiesService.updateAuthorities(author,userDetails.getAuthorities());
    }

    @Override
    public void deleteUser(String s) {
        authorService.delete(s);
    }

    @Override
    public void changePassword(String s, String s1) {
        authorService.changePassword(s, encoder.encode(s1));
    }

    @Override
    public boolean userExists(String s) {
        return authorService.exist(s);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User(authorService.findByUsername(s));

    }

}
