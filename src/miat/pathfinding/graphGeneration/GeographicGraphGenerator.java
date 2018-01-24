package miat.pathfinding.graphGeneration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;

import miat.pathfinding.graph.BenchmarkGraph;


public class GeographicGraphGenerator {

		public BenchmarkGraph<Coordinate, DefaultEdge> generateFromShapefile(String path, boolean directed) throws IOException{
			BenchmarkGraph<Coordinate, DefaultEdge> g = new BenchmarkGraph<>();
			File file = new File(path);
		    Map<String, Object> map = new HashMap<>();
		    map.put("url", file.toURI().toURL());

		    DataStore dataStore = DataStoreFinder.getDataStore(map);
		    String typeName = dataStore.getTypeNames()[0];

		    FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore
		            .getFeatureSource(typeName);
		    Filter filter = Filter.INCLUDE; 

		    FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
		    EdgeFactory<Coordinate, DefaultEdge> factory = new ClassBasedEdgeFactory<Coordinate,DefaultEdge>(DefaultEdge.class);
			Graph<Coordinate,DefaultEdge> newGraph = directed ? new SimpleDirectedGraph<Coordinate,DefaultEdge>(factory, true) : new SimpleGraph<Coordinate,DefaultEdge>(factory, true);
			
		    try (FeatureIterator<SimpleFeature> features = collection.features()) {
		    	int i = 0;
		        while (features.hasNext()) {
		            SimpleFeature feature = features.next();
		           	Geometry line = (Geometry) feature.getDefaultGeometry();
		           	
		           	Coordinate pt0 = line.getCoordinates()[0];
		           	Coordinate ptL = line.getCoordinates()[line.getCoordinates().length - 1];
		        	if (pt0.equals(ptL)) continue;
		        	
		           	if (!g.getVerticesIndex().containsKey(pt0)) {
		           		g.getVerticesIndex().put(pt0, i);
		           		i++;
		           	}
		        	if (!g.getVerticesIndex().containsKey(ptL)) {
		           		g.getVerticesIndex().put(ptL, i);
		           		i++;
		           	}
		        	newGraph.addVertex(pt0);
		        	newGraph.addVertex(ptL);
		        	DefaultEdge edge = new DefaultEdge(line);
		           	newGraph.addEdge(pt0, ptL,edge);
		        }
		    }
		    for (DefaultEdge edge : newGraph.edgeSet()) {
		    	newGraph.setEdgeWeight(edge,((Geometry) edge.getUserObject()).getLength());
		    }
		    g.setGraph(newGraph);
		    return g;
		}
		
}
