package au.azzmosphere.challengesolver2.services;

import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;
import au.azzmosphere.challengesolver2.persist.config.ConfigEntityManager;
import au.azzmosphere.challengesolver2.persist.config.CategoryDAO;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.net.URI;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;



/**
 * Created by aaron.spiteri on 11/7/17.
 */
public class TestConfigEntityManagerService {
    private static final ConfigEntityManagerService.CONFIG_KEYS CK_ID = ConfigEntityManagerService.CONFIG_KEYS.ID;
    private static final ConfigEntityManagerService.CONFIG_KEYS CK_HEADING = ConfigEntityManagerService.CONFIG_KEYS.HEADING;
    private static final ConfigEntityManagerService.CONFIG_KEYS CK_DESCRIPTION = ConfigEntityManagerService.CONFIG_KEYS.DESCRIPTION;
    private static final ConfigEntityManagerService.CONFIG_KEYS CK_URI = ConfigEntityManagerService.CONFIG_KEYS.URI;
    private static final ConfigEntityManagerService.CONFIG_KEYS CK_VIEW = ConfigEntityManagerService.CONFIG_KEYS.VIEW;
    private static final ConfigEntityManagerService.CONFIG_KEYS CK_ENABLED = ConfigEntityManagerService.CONFIG_KEYS.ENABLED;
    private static final ConfigEntityManagerService.CONFIG_KEYS CK_CLAZZ = ConfigEntityManagerService.CONFIG_KEYS.CLAZZ;

    private ConfigEntityManager configEntityManager = mock(ConfigEntityManager.class);
    private ConfigEntityManagerService configEntityManagerService = new ConfigEntityManagerService();

    @Before
    public void setupTest() {
        configEntityManagerService.setConfigEntityManager(configEntityManager);
    }

    @Test
    public void testRetrieveCategories1() throws Exception {
        CategoryDAO c1 = mock(CategoryDAO.class), c2 = mock(CategoryDAO.class), c3 = mock(CategoryDAO.class);

        when(c1.isEnabled()).thenReturn(true);
        when(c1.getId()).thenReturn(1);
        when(c1.getHeading()).thenReturn("heading 1");

        when(c2.isEnabled()).thenReturn(false);
        when(c2.getId()).thenReturn(2);
        when(c2.getHeading()).thenReturn("heading 2");

        when(c3.isEnabled()).thenReturn(true);
        when(c3.getId()).thenReturn(3);
        when(c3.getHeading()).thenReturn("heading 3");

        CategoryDAO[] categories = new CategoryDAO[] {
            c1, c2, c3,
        };
        when(configEntityManager.getCategories()).thenReturn(Arrays.asList(categories));
        List<HashMap<String, Object>> list =  configEntityManagerService.retrieveCategories();

        assertThat(list.size(), is(2));
        assertThat(list.get(0).get(CK_ID.toString()), is(1));
        assertThat(list.get(0).get(CK_HEADING.toString()), is("heading 1"));

        assertThat(list.get(1).get(CK_ID.toString()), is(3));
        assertThat(list.get(1).get(CK_HEADING.toString()), is("heading 3"));
    }

    @Test
    public void testRetrieveChallenges() throws Exception {
        ChallengeDAO c1 = mock(ChallengeDAO.class), c2 = mock(ChallengeDAO.class), c3 = mock(ChallengeDAO.class),
            c4 = mock(ChallengeDAO.class), c5 = mock(ChallengeDAO.class);

        ChallengeDAO[] challenges1 = new ChallengeDAO[] {
            c1, c2, c3,
        };
        ChallengeDAO[] challenges2 = new ChallengeDAO[] {c4, c5, };

        int i = 1;
        for (ChallengeDAO c : challenges1) {
            when(c.getId()).thenReturn(i);
            when(c.getHeading()).thenReturn("heading " + i);
            when(c.isEnabled()).thenReturn(true);
            i++;
        }
        for (ChallengeDAO c : challenges2) {
            when(c.getId()).thenReturn(i);
            when(c.getHeading()).thenReturn("heading " + i);
            when(c.isEnabled()).thenReturn(true);
            i++;
        }
        when(c2.isEnabled()).thenReturn(false);

        when(configEntityManager.getChallenges(1)).thenReturn(Arrays.asList(challenges1));
        when(configEntityManager.getChallenges(2)).thenReturn(Arrays.asList(challenges2));

        List<HashMap<String, Object>> list =  configEntityManagerService.retrieveChallenges(1);
        assertThat(list.size(), is(2));
        assertThat(list.get(0).get(CK_ID.toString()), is(1));
        assertThat(list.get(0).get("heading"), is("heading 1"));

        assertThat(list.get(1).get(CK_ID.toString()), is(3));
        assertThat(list.get(1).get("heading"), is("heading 3"));

        list =  configEntityManagerService.retrieveChallenges(2);
        assertThat(list.size(), is(2));
        assertThat(list.get(0).get(CK_ID.toString()), is(4));
        assertThat(list.get(0).get("heading"), is("heading 4"));

        assertThat(list.get(1).get(CK_ID.toString()), is(5));
        assertThat(list.get(1).get("heading"), is("heading 5"));
    }

    @Test
    public void testRetrieveCategory() throws Exception {
        CategoryDAO c = mock(CategoryDAO.class);

        when(c.getUri()).thenReturn(new URI("http://www.example.com"));
        when(c.getId()).thenReturn(1);
        when(c.getHeading()).thenReturn("heading 1");
        when(c.getDescription()).thenReturn("description 1");
        when(c.getView()).thenReturn("standardCategoryView1.html");
        when(c.isEnabled()).thenReturn(true);

        when(configEntityManager.getCategory(1)).thenReturn(c);
        HashMap<String, Object> categoryHash = configEntityManagerService.retrieveCategory(1);

        assertThat(categoryHash.get(CK_ID.toString()), is(1));
        assertThat(categoryHash.get(CK_URI.toString()), is(new URI("http://www.example.com")));
        assertThat(categoryHash.get(CK_HEADING.toString()), is("heading 1"));
        assertThat(categoryHash.get(CK_DESCRIPTION.toString()), is("description 1"));
        assertThat(categoryHash.get(CK_VIEW.toString()), is("standardCategoryView1.html"));
        assertThat(categoryHash.get(CK_ENABLED.toString()), is(true));
    }

    @Test
    public void testRetrieveChallenge() throws Exception {
        ChallengeDAO c = mock(ChallengeDAO.class);

        when(c.getClazz()).thenReturn("this.class");
        when(c.getId()).thenReturn(1);
        when(c.getHeading()).thenReturn("this heading");
        when(c.getView()).thenReturn("standardview.html");
        when(c.isEnabled()).thenReturn(true);
        when(c.getDescription()).thenReturn("this description");

        when(configEntityManager.getChallenge(1, 1)).thenReturn(c);

        HashMap<String, Object> challengeHash = configEntityManagerService.retrieveChallenge(1, 1);

        assertThat(challengeHash.get(CK_CLAZZ.toString()), is("this.class"));
        assertThat(challengeHash.get(CK_ID.toString()), is(1));
        assertThat(challengeHash.get(CK_HEADING.toString()), is("this heading"));
        assertThat(challengeHash.get(CK_VIEW.toString()), is("standardview.html"));
        assertThat(challengeHash.get(CK_ENABLED.toString()), is(true));
        assertThat(challengeHash.get(CK_DESCRIPTION.toString()), is("this description"));
    }
}
