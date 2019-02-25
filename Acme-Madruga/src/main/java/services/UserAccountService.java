
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

		Assert.notNull(authorityName);

		final UserAccount res = new UserAccount();

		res.setAuthorities(new ArrayList<Authority>());

		final Authority authority = new Authority();

		authority.setAuthority(authorityName);

		final Collection<Authority> authorities = res.getAuthorities();

		authorities.add(authority);

		res.setAuthorities(authorities);

		return res;
	}

	//FINDONE
	public UserAccount findOne(final int userAccountId) {

		UserAccount res = null;
		res = this.userAccountRepository.findOne(userAccountId);

		return res;
	}

	//FINDALL
	public Collection<UserAccount> findAll() {

		Collection<UserAccount> res = null;
		res = this.userAccountRepository.findAll();

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
