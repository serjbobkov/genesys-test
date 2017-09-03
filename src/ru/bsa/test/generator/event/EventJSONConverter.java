package ru.bsa.test.generator.event;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.TimeZone;

public class EventJSONConverter {

    private static DecimalFormat formatter = new DecimalFormat("#00.###");


    public static String toJSON(Event e) {
        StringBuilder b = new StringBuilder();
        b.append("{");
        b.append("\"AgentId\":\"").append(e.getAgentId() == null ? "undefined" : e.getAgentId()).append("\",");
        b.append("\"CreateTime\":\"").append(convertDate2Json(e.getCreateTime())).append("\",");
        b.append("\"DeliveryTime\":\"").append(convertDate2Json(e.getDeliveryTime())).append("\",");
        b.append("\"EndReason\":\"").append(e.getEndReason() == null ? "undefined" : e.getEndReason().getTitle()).append("\",");
        b.append("\"EndTime\":\"").append(convertDate2Json(e.getEndTime())).append("\",");
        b.append("\"EventTimeStamp\":\"").append(convertDate2Json(e.getEventTimeStamp())).append("\",");
        b.append("\"EventType\":\"").append(e.getEventType() == null ? "undefined" : e.getEventType().getTitle()).append("\",");
        b.append("\"Id\":\"").append(e.getId() == null ? "undefined" : e.getId().toString()).append("\",");
        b.append("\"OriginationChannel\":\"").append(e.getOriginationChannel() == null ? "undefined" : e.getOriginationChannel().getTitle()).append("\",");
        b.append("\"OriginationPage\":\"").append(e.getOriginationPage() == null ? "undefined" : e.getOriginationPage().getTitle()).append("\",");
        b.append("\"ServiceType\":\"").append(e.getServiceType() == null ? "undefined" : e.getServiceType().getTitle()).append("\"");
        b.append("}");
        return b.toString();
    }


    private static String convertDate2Json(Date date) {
        if (date == null) {
            return convertDate2Json(new Date(0L));
        } else {
            int zoneOffsetMillisecond = TimeZone.getDefault().getOffset(date.getTime());
            String sign = "+";
            if (zoneOffsetMillisecond < 0) { // negative offset
                sign = "-";
                zoneOffsetMillisecond *= -1;
            }
            int minute = (int) (zoneOffsetMillisecond % 60L * 60 * 1000);

            int hour = (zoneOffsetMillisecond / 1000 / 60 / 60);
            return "/Date(" + date.getTime() + sign + formatter.format(hour) + formatter.format(minute) + ")/";
        }
    }

}
