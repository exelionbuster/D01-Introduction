
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
public class UserAccountService {

	public UserAccountService() {
		super();
	}


	//Managed repository ------------------------------------------------------

	@Autowired
	private UserAccountRepository	userAccountRepository;


	//Supporting services -----------------------------------------------------

	//CRUD methods -----------------------------------------------------

	//CREATE
	public UserAccount create(final String authorityName) {
		//Compruebo que el parámetro no es null
		Assert.notNull(authorityName);

		final UserAccount res = new UserAccount();

		res.setAuthorities(new ArrayList<Authority>());

		final Authority authority = new Authority();

		authority.setAuthority(authorityName);

		final Collection<Authority> authorities = res.getAuthorities();

		authorities.add(authority);

		return res;
	}

	//FINDONE
	public UserAccount findOne(final int userAccountId) {
		//Declaro un userAccount vacío
		UserAccount res = null;
		//Traigo el userAccount
		res = this.userAccountRepository.findOne(userAccountId);
		//Devuelve el userAccount si lo encuentra
		return res;
	}

	//FINDALL
	public Collection<UserAccount> findAll() {
		//Declaro colección vacía
		Collection<UserAccount> res = null;
		//Traigo los userAccounts
		res = this.userAccountRepository.findAll();
		//Devuelvo la colección si encuentra
		return res;
	}

	//SAVE
	public UserAccount save(final UserAccount userAccount) {

		UserAccount res;

		Assert.notNull(userAccount);
		Assert.notNull(userAccount.getUsername());
		Assert.notNull(userAccount.getPassword());

		res = this.userAccountRepository.save(userAccount);

		return res;
	}

	// Other business methods -------------------------------------------------

	//Method to encrypt the password
	public void encodePassword(final UserAccount userAccount) {

		String pass = null;

		try {
			pass = new Md5PasswordEncoder().encodePassword(userAccount.getPassword(), null);
			userAccount.setPassword(pass);

		} catch (final Exception e) {
			System.out.println("Exception catched in Md5PasswordEncoder: " + e.toString());
		}

	}

}
