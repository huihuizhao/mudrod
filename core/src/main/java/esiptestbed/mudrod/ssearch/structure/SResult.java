/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you 
 * may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package esiptestbed.mudrod.ssearch.structure;

import java.lang.reflect.Field;

/**
 * Data structure class for search result 
 */
public class SResult {
  public static final String rlist[] = {"term_score", "releaseDate_score", "versionNum_score", "processingL_score", 
      "allPop_score", "monthPop_score", "userPop_score", "spatialR_score", "temporalR_score", "click_score"};
  String shortName = null;
  String longName = null;
  String topic = null;
  String description = null;
  String relase_date = null;

  public Double final_score = 0.0;
  public Double term_score = 0.0;
  public Double releaseDate_score = 0.0;
  public Double versionNum_score = 0.0;
  public Double processingL_score = 0.0;
  public Double click_score = 0.0;
  public Double allPop_score = 0.0;
  public Double monthPop_score = 0.0;
  public Double userPop_score = 0.0;
  public Double spatialR_score = 0.0;
  public Double temporalR_score = 0.0;
  public Integer below = 0;
  
  public String version = null;
  public String processingLevel = null;
  public String latency = null;
  public String stopDateLong = null;
  public String stopDateFormat = null;
  public Double spatialR = null;
  public Double temporalR = null;
  
  public Double geoSim = null;
  
  public Double releaseDate = null;
  public Double click = null;
  public Double term = null;
  public Double versionNum = null;
  public Double processingL = null;
  public Double allPop = null;
  public Double monthPop = null;
  public Double userPop = null;
  public Double termAndv = null;
  
  public Double prediction = 0.0;
  public String label = null;

  /**
   * @param shortName short name of dataset
   * @param longName long name of dataset
   * @param topic topic of dataset
   * @param description description of dataset
   * @param date release date of dataset
   */
  public SResult(String shortName, String longName, String topic, String description, String date){
    this.shortName = shortName;
    this.longName = longName;
    this.topic = topic;
    this.description = description;
    this.relase_date = date;
  }
  
  public SResult(SResult sr){
    for(int i =0; i <rlist.length; i++)
    {
      set(this, rlist[i], get(sr, rlist[i]));
    }
  }
  
  /**
   * Method of getting export header
   * @param delimiter the delimiter used to separate strings
   * @return header
   */
  public static String getHeader(String delimiter){
    String str = "";
    for(int i =0; i <rlist.length; i++)
    {
      str += rlist[i] + delimiter;
    }
    //return "ShortName" + delimiter + /*"geoSim" + delimiter +*/ str + "label" + "\n"; //for searcher.java
    str = str + "label" + "\n";
    return str;
  }
 
  /**
   * Method of get a search results as string
   * @param delimiter the delimiter used to separate strings
   * @return search result as string
   */
  public String toString(String delimiter){
    String str = "";
//    for(int i =0; i <rlist.length; i++)
//    {
//      double score = get(this, rlist[i]);
//      str += score + delimiter;
//    }
    
    for(int i =0; i <rlist.length; i++)
    {
      String att = SResult.rlist[i].split("_")[0];
      double score = (double) SResult.get(this, att);
      str += score + delimiter;
    }
    
    //return shortName + delimiter + /*geoSim + delimiter +*/ str + label + "\n";
    str = str + label + "\n";
    return str;
  }
  
  public SResult minus(SResult sr1){
    SResult minusResult = new SResult(null, null, null, null, null);
    for(int i =0; i <rlist.length; i++)
    {
      String att = SResult.rlist[i].split("_")[0];
      String scoreId = SResult.rlist[i];
      double score = (double) SResult.get(this, att) - (double) SResult.get(sr1, att);
      SResult.set(minusResult, scoreId, score);
    }
    
    return minusResult;
  }

  /**
   * Generic setter method
   * @param object instance of SResult
   * @param fieldName field name that needs to be set on
   * @param fieldValue field value that needs to be set to
   * @return 1 means success, and 0 otherwise
   */
  public static boolean set(Object object, String fieldName, Object fieldValue) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
      try {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, fieldValue);
        return true;
      } catch (NoSuchFieldException e) {
        clazz = clazz.getSuperclass();
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
    }
    return false;
  }

  /**
   * Generic getter method
   * @param object instance of SResult
   * @param fieldName field name of search result
   * @param <V> data type
   * @return the value of the filed in the object
   */
  @SuppressWarnings("unchecked")
  public static <V> V get(Object object, String fieldName) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
      try {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (V) field.get(object);
      } catch (NoSuchFieldException e) {
        clazz = clazz.getSuperclass();
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
    }
    return null;
  }

}
