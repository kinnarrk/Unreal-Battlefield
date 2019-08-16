/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Kinnar
 */
public class AnimationStorage {    
    private Map<String, List<List<String>>> hashMap;
    private List<List<String>> parentRoute;

    public Map<String, List<List<String>>> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, List<List<String>>> hashMap) {
        this.hashMap = hashMap;
    }   

    public List<List<String>> getParentRoute() {
        return parentRoute;
    }

    public void setParentRoute(List<List<String>> parentRoute) {
        this.parentRoute = parentRoute;
    }        
}
