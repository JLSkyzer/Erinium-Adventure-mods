# Erinium-Adventure-Mod
<a href="https://discord.gg/FBR2HEbbgm"><img alt="Discord" src="https://img.shields.io/discord/927941401805746236?style=for-the-badge&logo=discord&logoColor=darkblue&label=Discord&labelColor=blackgray&color=darkblue" witdh="285" height="40"></a> </br>
 Erinium Adventure's mods
---
Erinium Jobs
This mod is enterly configurable ! You can add block and item to earn XP and set a level requirement between 0 and 100 for blocks and item !
### Earn XP
To add a block or item to earn money go to these location : 
GAMEFOLDER/config/EriniumJobs/GainXp/MODID/ID

The file must be a json file like iron_ingot.json
Here the json parameter : 
```json
{
	"min-level": "LEVEL",
	"max-level": "LEVEL",
	"type": "TYPE",
	"xp": "XP"
}
```
- min level is the minium level to start to earn xp
- max level is the max level to earn xp (after you dont earn)
- type is the type here an example :

	- BREAK (When you break block)
	- EAT (When you eat / drink)
	- SMELTED (An item smelted ⚠️RESULT ITEM*)
	- CRAFTED (An item crafted ⚠️RESULT ITEM*)

\* For example, for diamond, on BREAK you set diamond_ore and on SMELTED you set diamond
- xp is the amount of XP is given (more you will levelup, you will earn less xp) see my [excel](https://1drv.ms/x/s!Aq5o6W9h7OB9gYExyFZm1cFhJ0n0EA?e=Ko20vF "excel") to understand
💡XP multiplier and Won XP Multiplier are fully customisable here : GAMEFOLDER/config/erinium-ranking.toml
💡You can set multiple type like that : "BREAK, EAT, CRAFTED" but generaly you don't need to set multiple type but why not

Here an example for diamond_ore and diamond
location : GAMEFOLDER/config/EriniumJobs/GainXp/minecraft/diamond_ore.json
```json
{
	"min-level": "0",
	"max-level": "45",
	"type": "BREAK",
	"xp": "125"
}
```
location : GAMEFOLDER/config/EriniumJobs/GainXp/minecraft/diamond.json
```json
{
	"min-level": "0",
	"max-level": "65",
	"type": "SMELTED",
	"xp": "160"
}
```

### Require Level
To add a requirement to an item or block go to these location : 
GAMEFOLDER/config/EriniumJobs/required/MODID/ID

Here the json parameter : 
```json
{
	"level": "LEVEL"
}
```
LEVEL is the required level between 0 and 100 (set 101 to completly disable an item or block !)
