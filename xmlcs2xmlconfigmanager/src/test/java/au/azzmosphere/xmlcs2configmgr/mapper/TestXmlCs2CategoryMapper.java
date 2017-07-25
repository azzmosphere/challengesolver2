package au.azzmosphere.xmlcs2configmgr.mapper;

import au.azzmosphere.challengesolver2.persist.config.CategoryDAO;
import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;
import au.azzmosphere.xmlcs2configmgr.xmlbindings.XmlCs2Category;
import au.azzmosphere.xmlcs2configmgr.xmlbindings.XmlCs2Challenge;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestXmlCs2CategoryMapper {
    private XMLCs2ChallengeMapper xmlCs2ChallengeMapper = mock(XMLCs2ChallengeMapper.class);
    private Map<Integer, Map<Integer, ChallengeDAO>> challenges = new HashMap<>();
    private XMLCs2CategoryMapper xmlCs2CategoryMapper = new XMLCs2CategoryMapper(challenges, xmlCs2ChallengeMapper);

    @Test
    public void mapItem1() throws Exception {

        XmlCs2Challenge xmlCs2Challenge[] = {mock(XmlCs2Challenge.class)};
        when(xmlCs2Challenge[0].getId()).thenReturn(1);
        when(xmlCs2Challenge[0].getClazz()).thenReturn("foo.class.ThisClass");
        when(xmlCs2Challenge[0].getHeading()).thenReturn("Some Heading");
        when(xmlCs2Challenge[0].getDescription()).thenReturn("A Description of this");
        when(xmlCs2Challenge[0].getView()).thenReturn("standardview.html");
        when(xmlCs2Challenge[0].isEnabled()).thenReturn(true);

        XmlCs2Category xmlCs2Category = mock(XmlCs2Category.class);

        List<XmlCs2Challenge> challenges = Arrays.asList(xmlCs2Challenge);

        when(xmlCs2Category.getChallenges()).thenReturn(challenges);
        when(xmlCs2Category.getDescription()).thenReturn("This description");
        when(xmlCs2Category.getEnabled()).thenReturn(true);
        when(xmlCs2Category.getHeading()).thenReturn("Category Heading");
        when(xmlCs2Category.getId()).thenReturn(3);
        when(xmlCs2Category.getUri()).thenReturn(new URI("www.example.com"));
        when(xmlCs2Category.getView()).thenReturn("categoryview.html");

        ChallengeDAO challengeDAO = mock(ChallengeDAO.class);
        when(challengeDAO.getId()).thenReturn(1);
        when(xmlCs2ChallengeMapper.mapItem(any(XmlCs2Challenge.class))).thenReturn(challengeDAO);

        CategoryDAO categoryDAO = xmlCs2CategoryMapper.mapItem(xmlCs2Category);

        Map categories = this.challenges.get(xmlCs2Category.getId());
        assertThat(categories.get(1), is(challengeDAO));
        assertThat(categoryDAO.getDescription(), is(xmlCs2Category.getDescription()));
        assertThat(categoryDAO.isEnabled(), is(xmlCs2Category.getEnabled()));
        assertThat(categoryDAO.getHeading(), is(xmlCs2Category.getHeading()));
        assertThat(categoryDAO.getId(), is(xmlCs2Category.getId()));
        assertThat(categoryDAO.getUri(), is(xmlCs2Category.getUri()));
        assertThat(categoryDAO.getView(), is(xmlCs2Category.getView()));
    }
}
