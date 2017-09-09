package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupsHelper().addFirstGroup(new GroupData("newGroup", null, null));
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupsHelper().getGroupList();
    app.getGroupsHelper().selectGroup(before.size() - 1);
    app.getGroupsHelper().initGroupModification();
    GroupData group = new GroupData( before.get(before.size() -1).getId(),"newGroup", "testHeader", "testFooter");
    app.getGroupsHelper().fillGroupForm(group);
    app.getGroupsHelper().submitGroupModification();
    app.getGroupsHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
