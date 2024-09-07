package org.rest.data.vo;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PersonVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*A entidade VO vai agir como intermediaria para encapsular a lógica de negócios e aumentar a segurança de forma que o client
	não tenha conhecimento da implementação da entidade real além de reduzir o excesso de chamadas de métodos e sobrecargas*/
	private final Long id; 
	private final String firstName;
	private final String lastName;
	private final String adress;
	private final String gender;

	@JsonCreator
	public PersonVO(@JsonProperty("firstName") Long id,
					@JsonProperty("firstName") String firstName, 
	                @JsonProperty("lastName") String lastName, 
	                @JsonProperty("adress") String adress, 
	                @JsonProperty("gender") String gender ) 
	{	this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.gender = gender;
	}


	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAdress() {
		return adress;
	}

	public Long getId() {
		return id;
	}


	public String getGender() {
		return gender;
	}


	@Override
	public int hashCode() {
		return Objects.hash(adress, firstName, gender, id, lastName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(adress, other.adress) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}


	

	
	
}
