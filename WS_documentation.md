# Websocket

header
```ts
{
	Authorization: String
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
		chats: Chat[],
		friends: Friend[]
	}
}
```

## Send Message to Chat
Send (Sender)
```ts
{
	op: 11,
	t: 1,
	d: {
		user: User
		message: Message
	}
}
```
Respond (Everyone in chat not Sender)
```ts
{
	op: 12,
	t: 2,
	d: {
		sender: User
		message: Message
	}
}
```
## Update Message in Chat
Send (Sender)
```ts
	op: 14,
	t: 1,
	d: {
		chat_id: Number
		message_id: Number
		message: String
	}
```
Respond (Everyone in chat not Sender)
```ts
	op: 15,
	t: 1,
	d: {
		chat_id: Number
		message_id: Number
		message: String
	}
```
## Delete Message in Chat
Send (Sender)
```ts
	op: 14,
	t: 1,
	d: {
		chat_id: Number
		message_id: Number
	}
```
Respond (Everyone in chat not Sender)
```ts
	op: 15,
	t: 1,
	d: {
		chat_id: Number
		message_id: Number
	}
```
## User Status
Respond (Auto send by Server)
```ts
	op: 14,
	t: 1,
	d: {
		user_ids: User[]
	}
```
## Add Friend
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
## Accept/Deny Friend request
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