/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controller;

import com.chat.model.User;

import com.chat.service.AddFriendService;

/**
 *
 * @author Marwa
 */
public class AddFriendController {

    public  void DeleteContactFromUser(User user, User Mainuser) {
        AddFriendService.DeleteContactFromUser(user,Mainuser);
    }

    public void addFriend(User user, User mainUser) {
        AddFriendService.addFriend(user, mainUser);
    }

    public void deleteFriendRequest(User user, User user0) {
        AddFriendService.deleteFriendRequest(user, user0);
    }

}
