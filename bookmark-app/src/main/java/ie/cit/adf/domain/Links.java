package ie.cit.adf.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class Links {
	
	@JsonProperty("linkList")
	private List<Link> links;

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	

}
