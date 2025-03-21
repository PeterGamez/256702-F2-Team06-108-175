# Websocket

## Table of Contents
- [Websocket](#websocket)
	- [Table of Contents](#table-of-contents)
	- [Body](#body)
	- [Start Connection](#start-connection)
	- [User Status](#user-status)
	- [Message](#message)
		- [Private Chat](#private-chat)
		- [Group Chat](#group-chat)
		- [Update Message in Chat](#update-message-in-chat)
		- [Delete Message in Chat](#delete-message-in-chat)
	- [Chat](#chat)
		- [Create Chat](#create-chat)
		- [Update Chat](#update-chat)
	- [Friend](#friend)
		- [Add Friend](#add-friend)
		- [Accept/Deny Friend request](#acceptdeny-friend-request)

## Body
header
```ts
{
	Authorization: Bearer Token
}
```
payload
```ts
{
    op: Number,
	t: Number,
	d: { }
}
```

## Start Connection
Send
```ts
```
Respond
```ts
{
	op: 1,
	t: 1,
	d: {
		chat_ids: Number[],
		friend_ids: Number[]
	}
}
```
## User Status
Respond (Auto send by Server)
```ts
	op: 2,
	t: 1,
	d: {
		user_ids: Number[]
	}
```
## Message
### Private Chat
Send (Sender)
```ts
{
	op: 11,
	t: 1,
	d: {
		chat_id: Number
		message: String
	}
}
```
Respond (Everyone in chat not Sender)
```ts
{
	op: 12,
	t: 1,
	d: {
		chat_id: Number
		message: String
	}
}
```
### Group Chat
Send (Sender)
```ts
{
	op: 11,
	t: 2,
	d: {
		chat_id: Number
		message: String
	}
}
```
Respond (Everyone in chat not Sender)
```ts
{
	op: 12,
	t: 2,
	d: {
		chat_id: Number
		sender_id: Number
		message: String
	}
}
```
### Update Message in Chat
Send (Sender)
```ts
	op: 11,
	t: 6,
	d: {
		chat_id: Number
		message_id: Number
		message: String
	}
```
Respond (Everyone in chat not Sender)
```ts
	op: 12,
	t: 6,
	d: {
		chat_id: Number
		message_id: Number
		message: String
	}
```
### Delete Message in Chat
Send (Sender)
```ts
	op: 11,
	t: 7,
	d: {
		chat_id: Number
		message_id: Number
	}
```
Respond (Everyone in chat not Sender)
```ts
	op: 12,
	t: 7,
	d: {
		chat_id: Number
		message_id: Number
	}
```
## Chat
### Create Chat
Send
```ts
	op: 13,
	t: 1,
	d: {
		user_ids: Number[]
		type: 1 | 2
	}
```
Respond
```ts
	op: 14,
	t: 1,
	d: {
		chat_id: Number
	}
```
### Update Chat
Send
```ts
	op: 13,
	t: 2,
	d: {
		chat_id: Number
		chat: String
		add_users: Number[]
		remove_users: Number[]
	}
```
Respond
```ts
	op: 14,
	t: 2,
	d: {
		chat_id: Number
		chat: String
		users: Number[]
	}
```
## Friend
### Add Friend
Sender
```ts
{
	op: 16,
	t: 1,
	d: {
		user_id: Number
	}
}
```
Respond
```ts
{
	op: 17,
	t: 1,
	d: {
		user_id: Number
	}
}
```
### Accept/Deny Friend request
- 1 Accept
- 2 Deny
Sender
```ts
{
	op: 16,
	t: 2,
	d: {
		user_id: Number,
		type: 1 | 2
	}
}
```
Respond
```ts
{
	op: 17,
	t: 2,
	d: {
		user_id: Number,
		type: 1 | 2
	}
}
```