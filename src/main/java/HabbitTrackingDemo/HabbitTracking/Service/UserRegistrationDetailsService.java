package HabbitTrackingDemo.HabbitTracking.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import HabbitTrackingDemo.HabbitTracking.Repo.UserRepository;
import HabbitTrackingDemo.HabbitTracking.Security.UserRegistrationDetails;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email)
				.map(UserRegistrationDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("user not found"));
	}

}
