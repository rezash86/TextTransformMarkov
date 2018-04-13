package com.markov.textTransform.model;

/**
 * This class represent the attributes of 
 * the text that is being processed and transformed
 * @author Reza.sh
 *
 */
public class TextInfo {
	
	//path of the text file
	private String path;
	
	//keysize in the algorithm
	private int keySize;
	
	//output size of the text file
	private int outputSize;
	
	//prefix that is used in the algorithm
	private String prefix;

	
	public TextInfo() {
	}
	
	public TextInfo(String path, int keySize, int outputSize) {
		this.path = path;
		this.keySize = keySize;
		this.outputSize = outputSize;
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getKeySize() {
		return keySize;
	}
	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}
	public int getOutputSize() {
		return outputSize;
	}
	public void setOutputSize(int outputSize) {
		this.outputSize = outputSize;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String toString() {
		return "TextInfo [path=" + path + ", keySize=" + keySize + ", outputSize=" + outputSize + ", prefix=" + prefix
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + keySize;
		result = prime * result + outputSize;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		TextInfo other = (TextInfo) obj;
		if (keySize != other.keySize)
			return false;
		if (outputSize != other.outputSize)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		return true;
	}
	
}
