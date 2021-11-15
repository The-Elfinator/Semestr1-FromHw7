package md2html;

import java.io.*;
import java.util.Arrays;

public class MyScanner {
	private static final int INT = 8192;
	private final BufferedReader scan;
    private char[] buffer;
    private int read;
    private StringBuilder line;
    private String savedLine;
	private String savedWord;
    private boolean sepOnBufBounds;
	private boolean foundSep;
    private int indStart;

    public MyScanner(InputStreamReader stream) throws IOException {
		this.scan = new BufferedReader(stream);
		this.buffer = new char[INT];
		this.line = new StringBuilder();
		this.savedLine = null;
		this.sepOnBufBounds = false;
		this.indStart = 0;
		this.foundSep = false;
		this.savedWord = null;
    }

	public MyScanner(String s) throws IOException {
		this.scan = new BufferedReader(new StringReader(s));
		this.buffer = new char[INT];
		this.line = new StringBuilder();
		this.indStart = 0;
		this.savedWord = null;
	}

    public boolean hasNextLine() throws IOException {
		this.savedLine = nextLine();
		return this.savedLine != null;
    }

    public String nextLine() throws IOException {
		if (this.savedLine != null) {
			String res = this.savedLine;
			this.savedLine = null;
			return res;
		}
		if ((this.buffer.length == 0) || ((int) this.buffer[0] == 0)) {
			this.read = this.scan.read(this.buffer);
		}
		while (this.read > -1) {
			int ind = -1;
			String sep = System.lineSeparator();
			boolean flag = false;
			int indStart = 0;
			if (this.sepOnBufBounds) {
				indStart = 1;
				this.sepOnBufBounds = false;
			}
			for (int i = indStart; i < this.read; i++) {
				for (int j = 0; j < sep.length(); j++) {
					if (this.buffer[i] == sep.charAt(j)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					ind = i;
					break;
				} else {
					this.line.append(this.buffer[i]);
				}
			}
			if ((ind != -1) && (ind != this.read - sep.length() + 1)) {
				String res = this.line.toString();
				this.buffer = Arrays.copyOfRange(this.buffer, ind + sep.length(), this.read);
				if (this.buffer.length == 0) {
					this.buffer = new char[INT];
					this.read = INT;
				} else {
					this.read = this.buffer.length;
				}
				this.line = new StringBuilder();
				return res;
			} else if (ind == -1) {
				this.buffer = new char[INT];
			} else {
				this.sepOnBufBounds = true;
				String res = this.line.toString();
				this.buffer = Arrays.copyOfRange(this.buffer, ind + 1, this.read);
				if (this.buffer.length == 0) {
					this.buffer = new char[INT];
					this.read = INT;
				} else {
					this.read = this.buffer.length;
				}
				this.line = new StringBuilder();
				return res;
			}
			this.read = this.scan.read(this.buffer);
		}
		if (this.line.length() != 0) {
	    	String res = this.line.toString();
	    	this.line = new StringBuilder();
	    	return res;
		}
		return null;
    }

    public boolean hasNext() throws IOException {
		if ((this.buffer.length == 0) || ((int) this.buffer[0] == 0)) {
	    	this.read = scan.read(this.buffer);
	    	this.indStart = 0;
		} else {
	    	return true;
		}
		return this.read > 0;
    }

	public boolean hasNextWord() throws IOException {
		this.savedWord = nextWord();
		return savedWord != null;
	}

    public String nextWord() throws IOException {
		if (this.savedWord != null) {
			String res = this.savedWord;
			this.savedWord = null;
			return res;
		}
		if ((this.buffer.length == 0) || ((int) this.buffer[0] == 0)) {
			this.read = scan.read(this.buffer);
		}
		while (this.read > -1) {
	    	String res;
	    	int ind = -1;
	    	int indFirstGood = 0;
			if (System.lineSeparator().indexOf(this.buffer[indFirstGood]) != -1) {
				if (System.lineSeparator().indexOf(this.buffer[indFirstGood]) ==
					System.lineSeparator().length() - 1 && System.lineSeparator().length() != 1) {
					this.foundSep = false;
				} else {
					if (!this.foundSep) {
						this.foundSep = true;
						if (this.line.length() == 0) {
							this.buffer = Arrays.copyOfRange(this.buffer, indFirstGood + 1, this.read);
							if (buffer.length == 0) {
								this.buffer = new char[INT];
								this.read = scan.read(this.buffer);
							} else {
								this.read = buffer.length;
							}
							return "";
						} else {
							res = line.toString();
							this.line = new StringBuilder();
							this.buffer = Arrays.copyOfRange(this.buffer, indFirstGood, this.read);
							this.read = buffer.length;
							this.foundSep = false;
							return res;
						}
					}
				}
			} else {
				this.foundSep = false;
			}
	    	while (!(Character.isLetter(this.buffer[indFirstGood])) &&
							!(Character.DASH_PUNCTUATION == Character.getType(this.buffer[indFirstGood])) &&
							!(this.buffer[indFirstGood] == '\'')) {
				if (System.lineSeparator().indexOf(this.buffer[indFirstGood]) != -1) {
					if (!this.foundSep && !(System.lineSeparator().indexOf(this.buffer[indFirstGood]) ==
							System.lineSeparator().length() - 1 && System.lineSeparator().length() != 1)) {
						this.foundSep = true;
						if (this.line.length() == 0) {
							this.buffer = Arrays.copyOfRange(this.buffer, indFirstGood + 1, this.read);
							if (buffer.length == 0) {
								this.buffer = new char[INT];
								this.read = scan.read(this.buffer);
							} else {
								this.read = buffer.length;
							}
							return "";
						} else {
							res = line.toString();
							this.line = new StringBuilder();
							this.buffer = Arrays.copyOfRange(this.buffer, indFirstGood, this.read);
							this.read = buffer.length;
							this.foundSep = false;
							return res;
						}
					}
				} else {
					this.foundSep = false;
				}
	        	indFirstGood++;
	        	if (indFirstGood == this.read) {
		    		if (this.line.length() != 0) {
						res = this.line.toString();
						this.line = new StringBuilder();
						this.buffer = new char[INT];
						return res;
		    		} else {
						this.buffer = new char[INT];
						break;
		    		}
				}
	    	}
	    	if ((this.line.length() != 0) && (indFirstGood != 0)) {
				res = this.line.toString();
				this.line = new StringBuilder();
				this.buffer = Arrays.copyOfRange(this.buffer, indFirstGood, this.read);
				this.read = this.buffer.length;
				return res;
			}
			for (int i = indFirstGood; i < this.read; i++) {
				if ((Character.DASH_PUNCTUATION == Character.getType(this.buffer[i])) ||
							(this.buffer[i] == '\'') || (Character.isLetter(this.buffer[i]))) {
						this.line.append(this.buffer[i]);
				} else {
					ind = i;
					break;
				}
			}
			if (ind != -1) {
				if (line.length() != 0) {
					res = this.line.toString();
					this.line = new StringBuilder();
					this.buffer = Arrays.copyOfRange(this.buffer, ind, this.read);
					this.read = this.buffer.length;
					return res;
				} else {
					this.buffer = Arrays.copyOfRange(this.buffer, ind, this.read);
					this.read = this.buffer.length;
				}
			} else {
				this.buffer = new char[INT];
			}
			this.read = scan.read(this.buffer);
		}
		if (this.line.length() != 0) {
			String res = this.line.toString();
			this.line = new StringBuilder();
			return res;
		}
		return null;
    }

    public int nextInt(int p) throws IOException, NumberFormatException {
		if ((this.buffer.length == 0) || ((int) this.buffer[0] == 0)) {
			this.read = scan.read(this.buffer);
		}
		while (this.read > -1) {
			String res;
			int ind = -1;
			int indFirstGood = 0;
			while (Character.isWhitespace(this.buffer[indFirstGood]) ||
					(!(Character.isAlphabetic(this.buffer[indFirstGood])) &&
							!(Character.isDigit(this.buffer[indFirstGood])))) {
				indFirstGood++;
				if (indFirstGood == this.read) {
					if (this.line.length() != 0) {
						res = this.line.toString();
						this.line = new StringBuilder();
						this.buffer = new char[INT];
						return Integer.parseUnsignedInt(res, p);
					} else {
						this.buffer = new char[INT];
						break;
					}
				}
			}
			if ((this.line.length() != 0) && (indFirstGood != 0)) {
				res = this.line.toString();
				this.line = new StringBuilder();
				this.buffer = Arrays.copyOfRange(this.buffer, indFirstGood, this.read);
				this.read = this.buffer.length;
				return Integer.parseUnsignedInt(res, p);
			}
			for (int i = indFirstGood; i < this.read; i++) {
				if (!Character.isWhitespace(this.buffer[i])) {
					if ((Character.isAlphabetic(this.buffer[i])) ||
							(Character.isDigit(this.buffer[i]))) {
						this.line.append(this.buffer[i]);
					} else {
						ind = i;
					}
				} else {
					ind = i;
					break;
				}
			}
			if (ind != -1) {
				if (line.length() != 0) {
					res = this.line.toString();
					this.line = new StringBuilder();
					this.buffer = Arrays.copyOfRange(this.buffer, ind, this.read);
					this.read = this.buffer.length;
					return Integer.parseUnsignedInt(res, p);
				} else {
					this.buffer = Arrays.copyOfRange(this.buffer, ind, this.read);
					this.read = this.buffer.length;
				}
			} else {
				this.buffer = new char[INT];
			}
			this.read = scan.read(this.buffer);
		}
		String res = this.line.toString();
		this.line = new StringBuilder();
		return Integer.parseUnsignedInt(res, p);
    }

    public void close() throws IOException {
   		this.scan.close();
    }
}