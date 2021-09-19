package ut;/*
/*
Objectives:
---------------
- Take your time. The goal is not to finish, but to demonstrate your approach to the problem.  Please "think out loud".
- Drive the implementation with tests.  Be sure to think through edge cases and boundary conditions.
- Don't be afraid to start small.
- Keep an eye on maintainability.
- Explain any tradeoffs you make in your implementation.


- Work through each step, in order, one at a time
- Feel free to use the Internet (google, stackoverflow, etc.) as necessary.


Exercise Description:
---------------------
Create a URL shortening service (Product Marketing has named it "later.ly") that allows the user to specify when a shortened URL will be active.
For example, I have a cute kitten website with a special offer that I want to link to, but the shortened link for it shouldn't work until a specified date.


Step 1:
-------
Given a url (eg: "http://cutekittens.com/specialoffer") get back a shortened url (eg: "http://later.ly/<uniquecharacterorcharacters>").

Step 2:
-------
Given a shortened url, get back the original url

Step 3:
-------
When shortening a URL, specify a *required* date for when the shortened url will be active.

Step 4:
-------
When shortening a URL, specify an *optional* date for the shortened url to expire.

Step 5:
-------
When retrieving a shortened URL, do not return if it's before the active date or after the expired date
*/

// Configuration

import java.util.ArrayList;
import java.util.List;
//import org.junit.runner.*;
//import static org.junit.Assert.*;

// Tests
public class Solution {
    public Solution() {
    }

    public static void main(String[] args) {
        //        JUnitCore.main("HasCycle");

    }

    //Step 2
    //    @Test
    public void longen_ValidShortUrl_ReturnsOriginalUrl() {
        //given
        UrlShortener shortener = new UrlShortener();
        String expectedUrl = "http://cutekittens.com/specialoffer";
        String shortUrl = shortener.shorten(expectedUrl);

        //when
        String actualUrl = shortener.longen(shortUrl);

        //then
        //        assertEquals(expectedUrl, actualUrl);
    }

    //Step 0
    //    @Test
    public void validate_ValidUrl_ValidationPasses() { //TODO add invalid input test case: null, "", "    "
        //given
        String url = "http://cutekittens.com/specialoffer";
        //when
        boolean isValid = new UrlShortener().validate(url);
        //then
        //        assertTrue(isValid);
    }

    //Step 1
    //    @Test
    public void shorten_ValidUrl_ReturnsShortUrl() {
        //given
        String originalUrl = "http://cutekittens.com/specialoffer";
        String expectedShortUrl = "http://later.ly/1";
        UrlShortener shortener = new UrlShortener();
        //when
        String shortUrl = shortener.shorten(originalUrl);
        //then
        //        assertEquals(expectedShortUrl, shortUrl);
    }

    // Implementation
    public class UrlShortener {

        private static final String SHORT_URL_PREFIX = "http://later.ly/";

        private List<String> urls = new ArrayList<>();

        /**
         * TODO add javadoc
         */
        public boolean validate(final String url) {
            return url.startsWith("http://");
        }

        /**
         * TODO add javadoc
         * <p>
         * 1. http://cutekittens.com/specialoffer -> http://later.ly/ljsdfksj
         * 2. http://cutekittens.com/specialoffer -> http://later.ly/hdfodwco
         */
        public String shorten(final String url) {
            urls.add(url);
            return SHORT_URL_PREFIX + urls.size(); //1
        }

        public String longen(final String url) {
            if (!url.startsWith(SHORT_URL_PREFIX)) {
                throw new IllegalArgumentException("wrong url: " + url);
            }

            //http://later.ly/A
            String index = url.substring(SHORT_URL_PREFIX.length());

            int idx;
            try {
                idx = Integer.parseInt(index);
            } catch (Exception e) {
                throw new IllegalArgumentException("wrong url: " + url);
            }

            //2 - http://later.ly/100
            if (urls.size() < idx) {
                throw new IllegalArgumentException("wrong url: " + url);
            }

            return urls.get(idx - 1);
        }

    }

}












