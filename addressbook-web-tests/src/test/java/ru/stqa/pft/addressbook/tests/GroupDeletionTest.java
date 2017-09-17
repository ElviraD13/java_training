package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        app.group().addFirstGroup(new GroupData().withName("new_Group2"));
        app.goTo().groupPage();
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before,after);
      }
}
