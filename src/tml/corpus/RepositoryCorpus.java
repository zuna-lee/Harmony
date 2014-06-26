/*******************************************************************************
 *  Copyright 2007, 2009 Jorge Villalon (jorge.villalon@uai.cl)
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at 
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0 
 *  	
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License.
 *******************************************************************************/
package tml.corpus;

/**
 * This class represents a corpus with all the documents in the repository
 * 
 * @author Jorge Villalon
 *
 */
public class RepositoryCorpus extends Corpus {

	/**
	 * Creates a {@link Corpus} with all documents in the repository
	 */
	public RepositoryCorpus() {
		this.luceneQuery = "type:document";
	}

}
