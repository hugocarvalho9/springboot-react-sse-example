import { useState, useEffect } from "react";
import "./App.css";

interface WeatherNotification {
  eventId: string;
  location: string;
  condition: string;
  temperature: number;
  windSpeed: number;
}

function App() {
  const [events, setEvents] = useState<WeatherNotification[]>([]);

  useEffect(() => {
    const eventSource = new EventSource("http://localhost:8080/stream-sse");

    eventSource.onopen = () => {
      console.log("SSE connection opened.");
    };

    eventSource.onmessage = (event) => {
      console.log("New SSE message received:", event.data);
      const newEvent: WeatherNotification = JSON.parse(event.data);
      setEvents((prevEvents) => [...prevEvents, newEvent]);
    };

    eventSource.onerror = (error) => {
      console.error("Error with SSE connection:", error);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div>
      <h1>Event Notifications</h1>
      <ul>
        {events.map((event) => (
          <li key={event.eventId}>
            {" "}
            {/* Use event.id as the key */}
            <strong>{event.location}</strong> - {event.condition} - Temperature:{" "}
            {event.temperature.toFixed(2)}°C - Wind Speed:{" "}
            {event.windSpeed.toFixed(2)} m/s
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
