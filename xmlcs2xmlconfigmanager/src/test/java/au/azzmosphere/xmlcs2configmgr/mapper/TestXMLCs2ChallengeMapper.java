package au.azzmosphere.xmlcs2configmgr.mapper;

import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;
import au.azzmosphere.xmlcs2configmgr.xmlbindings.XmlCs2Challenge;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class TestXMLCs2ChallengeMapper {
    private XMLCs2ChallengeMapper xmlCs2ChallengeMapper = new XMLCs2ChallengeMapper();

    @Test
    public void mapDAO1() throws Exception {
        XmlCs2Challenge challenge = mock(XmlCs2Challenge.class);
        when(challenge.getId()).thenReturn(1);
        when(challenge.getClazz()).thenReturn("foo.class.ThisClass");
        when(challenge.getHeading()).thenReturn("Some Heading");
        when(challenge.getDescription()).thenReturn("A Description of this");
        when(challenge.getView()).thenReturn("standardview.html");
        when(challenge.isEnabled()).thenReturn(true);

        ChallengeDAO dao = xmlCs2ChallengeMapper.mapItem(challenge);
        assertThat(dao.getId(), is(1));
        assertThat(dao.getClazz(), is("foo.class.ThisClass"));
        assertThat(dao.getHeading(), is("Some Heading"));
        assertThat(dao.getDescription(), is("A Description of this"));
        assertThat(dao.getView(), is("standardview.html"));
        assertThat(dao.isEnabled(), is(true));
    }
}
