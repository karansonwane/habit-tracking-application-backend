package HabbitTrackingDemo.HabbitTracking.Model;

public class HabitDTO {
	  private String name;
	    private String plan;
	    
	    
		public HabitDTO(String name, String plan) {
			this.name = name;
			this.plan = plan;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getPlan() {
			return plan;
		}


		public void setPlan(String plan) {
			this.plan = plan;
		}

		
}
