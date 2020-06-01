package com.camomila.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;
/**
 * For another version HATEOAS:
 * import org.springframework.hateoas.RepresentationModel;
 */
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "address", "firstName", "lastName", "gender", "enabled"})
/**
 * For another version HATEOAS:
 * public class PersonVO extends RepresentationModel implements Serializable {
  */
public class PersonVO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String firstName;

    /**
     * Annotation to modify the attribute's display mode by the client (for example, Postman).
     * @JsonProperty("last_name")
     */

    private String lastName;

    private String address;

    /**
     * Annotation to ignore the attribute in the view by the client (for example, Postman).
     * @JsonIgnore
     */
    private String gender;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    private Boolean enabled;

    public PersonVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonVO personVO = (PersonVO) o;
        return key.equals(personVO.key) &&
                firstName.equals(personVO.firstName) &&
                lastName.equals(personVO.lastName) &&
                address.equals(personVO.address) &&
                gender.equals(personVO.gender) &&
                enabled.equals(personVO.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, firstName, lastName, address, gender, enabled);
    }
}
