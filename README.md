# Erinium-Adventure-Mod
<a href="https://discord.gg/FBR2HEbbgm"><img alt="Discord" src="https://img.shields.io/discord/927941401805746236?style=for-the-badge&logo=discord&logoColor=darkblue&label=Discord&labelColor=blackgray&color=darkblue" witdh="285" height="40"></a> </br>
 Erinium Adventure's mods
---
## Erinium Jobs
This mod is enterly configurable ! You can add block and item to earn XP and set a level requirement between 0 and 100 for blocks and item !
### Custom Jobs
By default, you have 4 jobs, miner, farmer, hunter and alchimist.
You can add custom jobs by adding json file at location : GAMEFOLDER/config/EriniumJobs/jobs/job_id.json

⚠️ You cant delete the fourth default jobs !
How the file is constructed : 
```json
{
	"displayname": "DISPLAYNAME"
}
```

### Earn XP
To add a block or item to earn money go to these location : 
GAMEFOLDER/config/EriniumJobs/EarnXp/MODID/ID.json

The file must be a json file like iron_ingot.json
Here the json parameter : 
```json
{
	"job_id": "JOB_ID",
	"min-level": LEVEL,
	"max-level": LEVEL,
	"type": "TYPE",
	"xp": XP
}
```
- job_id is the job you earn (one job only !)
- min level is the minium level to start to earn xp
- max level is the max level to earn xp (after you dont earn)
- type is the type here an example :

		- BREAK (When you break block)
		- EAT (When you eat / drink)
		- SMELTED (An item smelted ⚠️RESULT ITEM*)
		- CRAFTED (An item crafted ⚠️RESULT ITEM*)
  		- KILL (Kill an entities)

\* For example, for diamond, on BREAK you set diamond_ore and on SMELTED you set diamond
- xp is the amount of XP is given (more you will levelup, you will earn less xp) see my [excel](https://1drv.ms/x/s!Aq5o6W9h7OB9gYExyFZm1cFhJ0n0EA?e=Ko20vF "excel") to understand

💡XP multiplier and Won XP Multiplier are fully customisable here : GAMEFOLDER/config/erinium-ranking.toml

💡You can set multiple type like that : "BREAK, EAT, CRAFTED" but generaly you don't need to set multiple type but why not

Here an example for diamond_ore and diamond
location : GAMEFOLDER/config/EriniumJobs/EarnXp/minecraft/diamond_ore.json
```json
{
	"job_id": "miner",
	"min-level": 0,
	"max-level": 45,
	"type": "BREAK",
	"xp": 125
}
```
location : GAMEFOLDER/config/EriniumJobs/EarnXp/minecraft/diamond.json
```json
{
	"job_id": "miner",
	"min-level": 0,
	"max-level": 65,
	"type": "SMELTED",
	"xp": 160
}
```

### Require Level
To add a requirement to an item or block go to these location : 
GAMEFOLDER/config/EriniumJobs/Required/MODID/ID

Here the json parameter : 
```json
{
	"job_id": "JOB_ID",
	"level": LEVEL
}
```
JOB_ID is the job id (only one !)
LEVEL is the required level between 0 and 100 (set 101 to completly disable an item or block !)

## Ericonomy

Ericonomy is basic economy mod
You can pay, give, set, take, reset like an other economy mod !
Here basic command :
- /pay (player) (amount) | To pay a player
- /money | To get your balance
For admin
To have admin access, go here MINECRAFTFOLDER/Ericonomy/accounts/YOURUUID.json and set "ericonomy.admin" to true. Here command :
- /money (player) | To get player balance
- /money reset (player) | To reset player balance
- /money take (player) (amount) | To take money from player
- /money set (player) (amount) | Set amount to player balance
- /money give (player) (amount) | Give money to player

To change default balance value go here:
MINECRAFTFOLDER/config/ericonomy-common.toml

## Erinium Auction House
One of my biggest mod, this mod add an action house to sell item to other players !

<span style='color: red;'>This mod require Ericonomy</span>

### Here some screen
![](https://media.discordapp.net/attachments/719811588076470312/1216060561872388136/image.png?ex=65ff03a7&is=65ec8ea7&hm=ade48c9e99b60700ad6b18887aeb54740884dc00d80bfcf463e7c4d23e68c642&=&format=webp&quality=lossless&width=810&height=430)
![](https://media.discordapp.net/attachments/719811588076470312/1216065337141886986/image.png?ex=65ff0819&is=65ec9319&hm=58c628dbc603849d24297ffd825ee4742b222717042040723fbb21f1cb0fa290&=&format=webp&quality=lossless&width=810&height=430)

### Here the list of command:
- /ah | To open the Auction house
- /ah sell | to sell items
- /ah theme | To change the theme of the Auction House

### FAQ
Q. how to delete an item i set in the Auction House ? 
A. Just click on buy button you will able to delete the item (you will not pay)

## Erilog

This is a basic log mod
how to use ?
/erilog (type) (info, warn, error, success) (message)
the file will be here MINECRAFTFOLDER/EriniumLogs/(TYPE)/LASTFILE.erilog
