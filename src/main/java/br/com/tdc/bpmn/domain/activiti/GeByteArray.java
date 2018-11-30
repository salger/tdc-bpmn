package br.com.tdc.bpmn.domain.activiti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "act_ge_bytearray")
public class GeByteArray {

	@Id
	@Column(name = "ID_")
	private String id;

	@Column(name = "REV_")
	private String revision;

	@Column(name = "NAME_")
	private String name;

	@Column(name = "DEPLOYMENT_ID_")
	private String deploymentId;

	@Column(name = "BYTES_")
	private byte[] bytes;

	@Column(name = "GENERATED_")
	private String generated;

	public GeByteArray() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getGenerated() {
		return generated;
	}

	public void setGenerated(String generated) {
		this.generated = generated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeByteArray other = (GeByteArray) obj;
		if (id == null) {
			return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", revision=" + revision + ", name=" + name + ", deploymentId=" + deploymentId
				+ ", generated=" + generated + "]";
	}

}