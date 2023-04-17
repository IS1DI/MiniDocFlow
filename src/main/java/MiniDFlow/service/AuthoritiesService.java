package MiniDFlow.service;

import MiniDFlow.entity.Author;
import MiniDFlow.entity.Authority;
import MiniDFlow.repository.AuthorRepository;
import MiniDFlow.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthoritiesService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Transactional
    public void updateAuthorities(Author author, Collection<? extends GrantedAuthority> grantedAuthorities){
        grantedAuthorities.stream().map(a ->  new Authority(a.getAuthority(), author)).forEach(authority -> authorityRepository.update(authority));
    }


    @Transactional(rollbackOn = UsernameNotFoundException.class)
    public List<GrantedAuthority> getAuthoritiesByUsername(String username){
        if(authorRepository.exist(username)) {
            List<Authority> authorities = authorityRepository.findAuthoritiesByUsername(username);
            return authorities.stream().map(authority -> new SimpleGrantedAuthority(
                    authority.getName()
            )).collect(Collectors.toList());
        }else{
            throw new UsernameNotFoundException("user not found");
        }
    }

}
