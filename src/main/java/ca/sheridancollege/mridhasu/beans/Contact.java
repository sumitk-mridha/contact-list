package ca.sheridancollege.mridhasu.beans;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contact {
	private int id;
	private String name;
	private String phno;
	private String addr;
	private String email;
}
