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
	
	//Managed repository ------------------------------------------------------
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	//Supporting services -----------------------------------------------------
	
	//CRUD methods -----------------------------------------------------
	
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
		//Declaro un userAccount vacío
		UserAccount res = null;
		//Traigo el userAccount
		res = this.userAccountRepository.findOne(userAccountId);
		//Devuelve el userAccount si lo encuentra
		return res;
	}
	
	//FINDALL
	public Collection<UserAccount> findAll(){
		//Declaro colección vacía
		Collection<UserAccount> res = null;
		//Traigo los userAccounts
		res = this.userAccountRepository.findAll();
		//Devuelvo la colección si encuentra
		return res;
	}
	
	//SAVE
	public UserAccount save(final UserAccount userAccount){
		//Declaro un userAccount vacío
		UserAccount res;
		
		//Compruebo que el userAccount por parámetro está completo
		Assert.notNull(userAccount);
		Assert.notNull(userAccount.getUsername());
		Assert.notNull(userAccount.getPassword());

		//Llamo a save del repositorio para salvar la userAccount
		res = this.userAccountRepository.save(userAccount);

		//Devuelvo la userAccount
		return res;
	}
	
	
	// Other business methods -------------------------------------------------
	
	//Method to encrypt the password
	public void encodePassword(final UserAccount userAccount) {
		//Declaro el String para devolver
		String pass = null;
		//Intento codificar la contraseña
		try{
			//Obtengo y guardo en pass la contraseña generada mediante el Md5PasswordEncoder
			pass = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
			//Actualizo el valor de la contraseña
			userAccount.setPassword(pass);
		}catch (Exception e) {
			System.out.println("Exception catched in Md5PasswordEncoder: " + e.toString());
		}
		
	}
	
	
	
	
}
