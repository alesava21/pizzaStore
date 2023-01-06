package it.prova.pizzastore.security;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.prova.materialrest.model.User;
import it.prova.materialrest.repository.user.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository utenteRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = utenteRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, getAuthorities(user));

	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String userRoles = user.getRuolo().getDescrizione();
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}

}
