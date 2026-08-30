package demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Receiver {
  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

  private AtomicInteger counter = new AtomicInteger();

  public void receiveMessage(String message) {
    LOGGER.info("Received <" + message + ">");
    counter.incrementAndGet();
  }

  public int getCount() {
    LOGGER.info("counter.get() " + counter.get());
    return counter.get();
  }
}
