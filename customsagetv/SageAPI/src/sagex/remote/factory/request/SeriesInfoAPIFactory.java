package sagex.remote.factory.request;

/**
 * Unofficial SageTV Generated File - Never Edit
 * Generated Date/Time: 2/28/10 1:49 PM
 * See Official Sage Documentation at <a href='http://download.sage.tv/api/sage/api/SeriesInfoAPIFactory.html'>SeriesInfoAPIFactory</a>
 * This Generated API is not Affiliated with SageTV.  It is user contributed.
 */

import java.util.Map;
import sagex.remote.RemoteRequest;
import sagex.remote.xmlrpc.RequestHelper;

public class SeriesInfoAPIFactory {
   public static RemoteRequest createRequest(String context, String command, String[] parameters) {
   if (command.equals("GetAllSeriesInfo")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAllSeriesInfo",parameters,null);
   }
   if (command.equals("GetSeriesTitle")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesTitle",parameters,Object.class);
   }
   if (command.equals("GetSeriesDescription")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesDescription",parameters,Object.class);
   }
   if (command.equals("GetSeriesHistory")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesHistory",parameters,Object.class);
   }
   if (command.equals("GetSeriesPremiereDate")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesPremiereDate",parameters,Object.class);
   }
   if (command.equals("GetSeriesFinaleDate")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesFinaleDate",parameters,Object.class);
   }
   if (command.equals("GetSeriesNetwork")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesNetwork",parameters,Object.class);
   }
   if (command.equals("GetSeriesDayOfWeek")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesDayOfWeek",parameters,Object.class);
   }
   if (command.equals("GetSeriesHourAndMinuteTimeslot")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesHourAndMinuteTimeslot",parameters,Object.class);
   }
   if (command.equals("HasSeriesImage")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"HasSeriesImage",parameters,Object.class);
   }
   if (command.equals("GetSeriesImage")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesImage",parameters,Object.class);
   }
   if (command.equals("GetNumberOfCharactersInSeries")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetNumberOfCharactersInSeries",parameters,Object.class);
   }
   if (command.equals("GetSeriesActor")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesActor",parameters,Object.class,int.class);
   }
   if (command.equals("GetSeriesActorList")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesActorList",parameters,Object.class);
   }
   if (command.equals("GetSeriesCharacter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesCharacter",parameters,Object.class,int.class);
   }
   if (command.equals("GetSeriesCharacterList")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSeriesCharacterList",parameters,Object.class);
   }
   throw new RuntimeException("Invalid SeriesInfoAPIFactory Command: "+command);
   }


}
