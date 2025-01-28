package com.model.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CatResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    protected Integer id;

    protected String item;

    public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("CatResponse:[")
			.append("id=").append(this.id).append(", ")
			.append("item=").append(this.item)
			.append("]");
	    return sb.toString();
	}

}
