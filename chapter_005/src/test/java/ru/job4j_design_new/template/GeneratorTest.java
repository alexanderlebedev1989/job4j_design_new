package ru.job4j_design_new.template;

import org.junit.Test;
import java.util.Map;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    @Test
    public void produce() {
        Generator generator = new GeneratorFirst();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Ivan Ivanov", "subject", "you");
        String result = generator.produce(template, values);
        String expected = "I am a Ivan Ivanov, Who are you";
        assertThat(result, is(expected));
    }

    @Test(expected = IllegalStateException.class)
    public void whenKeysAreOnlyInTheTemplateThenAnException() {
        Generator generator = new GeneratorFirst();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("na", "Ivan Ivanov", "subj", "you");
        String result = generator.produce(template, values);
    }

    @Test(expected = IllegalStateException.class)
    public void whenExtraKeysInTheMapThenAnException() {
        Generator generator = new GeneratorFirst();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Ivan Ivanov",
                "subject", "you",
                "country", "Russia"
        );
        String result = generator.produce(template, values);
    }
}
