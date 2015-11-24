package server;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class User {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    final String token;
    String name;
    String chatRoom;
    Queue<String> messages = new LinkedList<String>();

    public User(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        lock.readLock().lock();
        String name = this.name;
        lock.readLock().unlock();
        return name;
    }

    public void setName(String name) {
        lock.writeLock().lock();
        this.name = name;
        lock.writeLock().unlock();
    }

    public String getChatRoom() {
        lock.readLock().lock();
        String chatRoom = this.chatRoom;
        lock.readLock().unlock();
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        lock.writeLock().lock();
        this.chatRoom = chatRoom;
        lock.writeLock().unlock();
    }

    public void addMessage(String message) {
        lock.writeLock().lock();
        messages.add(message);
        lock.writeLock().unlock();
    }

    public String getMessage() {
        lock.writeLock().lock();
        String message = messages.poll();
        lock.writeLock().lock();
        return message;
    }
}
