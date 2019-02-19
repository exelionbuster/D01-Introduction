package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class UserAccountService  {

	public UserAccountService(){
		super();
	}
	
	// Managed repository

	@Autowired
	private UserAccountRepository userAccountRepository;

	// Supporting services 
	
	//CRUDs
	
	//CREATE
	public UserAccount create(final String authorityName){
		//Compruebo que el parámetro no es null
		Assert.notNull(authorityName);
		
		UserAccount res = new UserAccount();
		
		res.setAuthorities(new ArrayList<Authority>());
		
		final Authority authority = new Authority();
		
		authority.setAuthority(authorityName);
		
		Collection<Authority> authorities = res.getAuthorities();
		
		authorities.add(authority);
		
		return res;	
	}
	
	//FINDONE
	public UserAccount findOne(final int userAccountId){
		
		UserAccount res = null;
		res = this.userAccountRepository.findOne(userAccountId);
		return res;
	}
	
	//FINDALL
	public Collection<UserAccount> findAll(){
		
		Collection<UserAccount> res = null;
		res = this.userAccountRepository.findAll();
		return res;
	}
	
	//SAVE
	public UserAccount save(final UserAccount userAccount){
		
		UserAccount res;
		
		//Compruebo que el userAccount por parámetro está completo
		Assert.notNull(userAccount);
		Assert.notNull(userAccount.getUsername());
		Assert.notNull(userAccount.getPassword());

		res = this.userAccountRepository.save(userAccount);

		return res;
	}
	
	// AUX
	
	//Metodo para encriptar la contraseña
	public void encodePassword(final UserAccount userAccount) {
		
		String pass = null;
		//Codificar la contraseña
		try{
			pass = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
			userAccount.setPassword(pass);
		}catch (Exception e) {
			System.out.println("Exception catched in Md5PasswordEncoder: " + e.toString());
		}
		
	}
	
	
	
	
}
