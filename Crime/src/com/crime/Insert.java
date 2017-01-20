package com.crime;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;

import arq.query;

public class Insert {
	static String word=null;
	static ArrayList<String> list = new ArrayList<String>();
	static String defaultNameSpace = "http://www.semanticweb.org/hamza/ontologies/2016/11/untitled-ontology-55#";
	public static void main(String[] args) throws IOException {
		String s ="occurOn";
		tokens(s);
		System.out.println(Analize2(list));

	}
	public  static ArrayList<String> tokens(String query) throws IOException
	{
		String[] words=query.split(" ");//splits the string based on string
		//using java foreach loop to print elements of string array
		for(String w:words){
		list.add(w);
		}
		return list;
	}
	public static Model Analize(ArrayList<String> arraylist) throws IOException
	{
		//String endpoint = "http://localhost:7200/repositories/Cricmantic";
		Model model = ModelFactory.createOntologyModel();
		model.read("C:\\Users\\Hamza\\workspace\\Crime\\crime.owl");
		Resource predicate;
		Property pred = null;
		int flag=0;
			word=(String) arraylist.get(1);
			predicate=model.getResource(defaultNameSpace+word);
			
			if(model.contains(predicate, RDF.type,OWL.ObjectProperty))
			{
			    pred=model.getProperty(defaultNameSpace+word);
				flag=1;
			}
			else if(model.contains(predicate, RDF.type,OWL.DatatypeProperty))
			{
				pred=model.getProperty(defaultNameSpace+word);
				flag=1;
			}
			if(flag==1)
			{
				word=(String) arraylist.get(0);
				Resource subject = model.createResource(defaultNameSpace+word);
				word=(String) arraylist.get(2);
				Resource object = model.createResource(defaultNameSpace+word);
				Resource class1=model.getResource(defaultNameSpace+"Match");
				model.add(subject,RDF.type,class1);
				model.add(object,RDF.type,class1);
				model.add(subject,pred,object);
				
			}
			return model;
		}
	public static ArrayList<String> Analize2(ArrayList<String> arraylist) throws IOException
	{
		String endpoint = "http://localhost:7200/repositories/Crime";
		int flag=0;
		Model model = ModelFactory.createOntologyModel();
		String QueryString=null;
		if(list.size()==2)
			{QueryString="PREFIX test:<"+defaultNameSpace+">\n"+
				"SELECT * where { \n"+
				"test:"+(String)list.get(0)+" test:"+(String)list.get(1)+"?r"+
				" } ";
			}
		else if(list.size()==1) 
		{
			flag=1;
			QueryString="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
					"PREFIX test:<"+defaultNameSpace+">\n"+
					"SELECT * where { \n"+
					"?r test:"+(String)list.get(0)+" ?z"+
					" } ";
		}
		Query query = QueryFactory.create(QueryString);
		QueryExecution q = QueryExecutionFactory.sparqlService(endpoint,query);
		ResultSet results = q.execSelect();
		list.clear();
		if(flag==1)
		{
			while (results.hasNext()) 
			{
				
				QuerySolution soln = results.nextSolution();
				list.add(soln.get("?r").toString().replaceAll(defaultNameSpace, "<br>"));
				list.add(soln.get("?z").toString().replaceAll(defaultNameSpace, "<br>"));
	
			}
		}
		else		
		{
		while (results.hasNext()) 
		{
			
			QuerySolution soln = results.nextSolution();
			list.add(soln.get("?r").toString().replaceAll(defaultNameSpace, "<br>"));
		}
		
		}
		return list;
	
	}
	public static void save(Model model) {
		String fileName = "save.rdf"; 
		FileWriter out;
		try 
		{ 
			out = new FileWriter(fileName );
			model.write( out, "RDF/XML" ); out.close(); 
			} 
		catch(IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}