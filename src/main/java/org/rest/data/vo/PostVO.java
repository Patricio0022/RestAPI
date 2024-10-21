package org.rest.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "title", "body"})
    public class PostVO extends RepresentationModel<org.rest.data.vo.PostVO> implements Serializable {

        private static final long serialVersionUID = 1L;

        @JsonProperty("id")
        @Mapping("id")
        private Long key;
        private String title;
        private String body;

        private PersonVO person;

        public PostVO() {}

        public Long getKey() {
            return key;
        }

        public void setKey(Long key) {
            this.key = key;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public PersonVO getPerson() {
            return person;
        }

        public void setPerson(PersonVO person) {
            this.person = person;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostVO postVO = (PostVO) o;
        return Objects.equals(key, postVO.key) &&
                Objects.equals(title, postVO.title) &&
                Objects.equals(body, postVO.body) &&
                Objects.equals(person, postVO.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, title, body, person);
    }
}