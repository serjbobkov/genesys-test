package ru.bsa.test.generator.event;



/*
*
*   Id	Guid	start		Unique interaction id
    EventType	String		“start”, “join” , “end” 	Applied sequentially for each ID.
    EventTimeStamp	DateTime			Timestamp of event
    CreateTime	DateTime	start		Equal to EventTimeStamp in the event “start”.
    DeliveryTime	DateTime	join	Has to be in a range 3-10 sec after CreateTime	Equal to EventTimeStamp in event “join”.
    EndTime	DateTime	end	Has to be in a range 15-60 sec after DeliveryTime.	Equal to EventTimeStamp in event “end”.
    ServiceType	String	start	"new account", "payment", "delivery"	Must be chosen randomly for each interaction.
    OriginationPage	String	start	"login", "balance", "transfer"	Must be chosen randomly for each interaction.
    AgentId	String	join	String in format Agent_{0:D3}"	Defined in the event “join”.
    EndReason	String	end	”Normal”, “Abnormal”	Defined in the event “end”
    OriginationChannel	String	start	“webchat”, “sms”, “wechat”	Must be chosen randomly for each interaction.
**/


import java.util.Date;
import java.util.UUID;

public class Event {


    private UUID id;
    private EventType eventType;
    private Date eventTimeStamp;
    private Date createTime;
    private Date deliveryTime;
    private Date endTime;
    private ServiceType serviceType;
    private OriginationPage originationPage;
    private String agentId;
    private EndReason endReason;
    private OriginationChannel originationChannel;


    public Event() {
    }

    public Event(UUID id, EventType eventType, Date eventTimeStamp, Date createTime, ServiceType serviceType, OriginationPage originationPage,
                 OriginationChannel originationChannel) {
        this.id = id;
        this.eventType = eventType;
        this.eventTimeStamp = eventTimeStamp;
        this.createTime = createTime;
        this.deliveryTime = deliveryTime;
        this.endTime = endTime;
        this.serviceType = serviceType;
        this.originationPage = originationPage;
        this.agentId = agentId;
        this.endReason = endReason;
        this.originationChannel = originationChannel;
    }

    private Event(UUID id, EventType eventType, Date eventTimeStamp, Date createTime, Date deliveryTime, Date endTime, ServiceType serviceType, OriginationPage originationPage, String agentId, EndReason endReason, OriginationChannel originationChannel) {
        this.id = id;
        this.eventType = eventType;
        this.eventTimeStamp = eventTimeStamp;
        this.createTime = createTime;
        this.deliveryTime = deliveryTime;
        this.endTime = endTime;
        this.serviceType = serviceType;
        this.originationPage = originationPage;
        this.agentId = agentId;
        this.endReason = endReason;
        this.originationChannel = originationChannel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Date getEventTimeStamp() {
        return eventTimeStamp;
    }

    public void setEventTimeStamp(Date eventTimeStamp) {
        this.eventTimeStamp = eventTimeStamp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public OriginationPage getOriginationPage() {
        return originationPage;
    }

    public void setOriginationPage(OriginationPage originationPage) {
        this.originationPage = originationPage;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public EndReason getEndReason() {
        return endReason;
    }

    public void setEndReason(EndReason endReason) {
        this.endReason = endReason;
    }

    public OriginationChannel getOriginationChannel() {
        return originationChannel;
    }

    public void setOriginationChannel(OriginationChannel originationChannel) {
        this.originationChannel = originationChannel;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType=" + eventType +
                ", eventTimeStamp=" + eventTimeStamp +
                ", createTime=" + createTime +
                ", deliveryTime=" + deliveryTime +
                ", endTime=" + endTime +
                ", serviceType=" + serviceType +
                ", originationPage=" + originationPage +
                ", agentId='" + agentId + '\'' +
                ", endReason=" + endReason +
                ", originationChannel=" + originationChannel +
                '}';
    }


    public Event copy() {
        return new Event(
                new UUID(id.getMostSignificantBits(), id.getLeastSignificantBits()),
                eventType,
                eventTimeStamp == null ? null : new Date(eventTimeStamp.getTime()),
                createTime == null ? null : new Date(createTime.getTime()),
                deliveryTime == null ? null : new Date(deliveryTime.getTime()),
                endTime == null ? null : new Date(endTime.getTime()),
                serviceType,
                originationPage,
                agentId,
                endReason,
                originationChannel
        );
    }
}
