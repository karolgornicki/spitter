package x.y.spitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import x.y.spitter.domain.Spitter;
import x.y.spitter.persistence.SpitterDao;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SpitterDao hibernateSpitterDao;
	@Autowired
	private Assembler assembler;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail)
			throws UsernameNotFoundException {
		
		Spitter userEntity = hibernateSpitterDao.findSpitterByUserameOrEmail(usernameOrEmail);
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return assembler.buildUserFromUserEntity(userEntity);
	}

}
