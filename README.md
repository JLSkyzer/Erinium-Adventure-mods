# Erinium-Adventure-Mod
<a href="https://discord.gg/FBR2HEbbgm"><img alt="Discord" src="https://img.shields.io/discord/927941401805746236?style=for-the-badge&logo=discord&logoColor=darkblue&label=Discord&labelColor=blackgray&color=darkblue" witdh="285" height="40"></a> </br>
 Erinium Adventure's mods
---
<h1 style="color: red;">Erinium Jobs</h1>
This mod is enterly configurable ! You can add block and item to earn XP and set a level requirement between 0 and 100 for blocks and item !
To add a block or item to earn money go to these location : 
<span style="color: blue;">GAMEFOLDER/config/EriniumJobs/GainXp/<span style="color: orange;">MODID</span>/<span style="color: orange;">ID</span></span>
The file must be a json file like iron_ingot.json
Here json parameter : 
```json
{
	"min-level": "LEVEL",
	"max-level": "LEVEL",
	"type": "TYPE",
	"xp": "XP"
}
```
min level is the minium level to start to earn xp
max level is the max level to earn xp (after you dont earn)
type is the type here an example :
	- BREAK (When you break block)
	- EAT (When you eat / drink)
	- SMELTED (An item smelted)
	- CRAFTED (An item crafted)
