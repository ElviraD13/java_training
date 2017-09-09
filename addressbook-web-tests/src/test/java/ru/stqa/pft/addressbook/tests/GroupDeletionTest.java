package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupsHelper().getGroupList();
        app.getGroupsHelper().selectGroup(before.size() - 1);
        app.getGroupsHelper().deleteSelectedGroup();
        app.getGroupsHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);
      }


}
