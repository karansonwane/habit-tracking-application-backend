package HabbitTrackingDemo.HabbitTracking.Serviceinterfaces;

import HabbitTrackingDemo.HabbitTracking.Model.LoginDTO;
import customeExeptions.response.LoginResponse;

public interface ILoginService {
	LoginResponse loginUser(LoginDTO loginDTO);

}
