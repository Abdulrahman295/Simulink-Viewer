## Simulink-Viewer
A software tool that reads Simulink MDL files and presents their hierarchical structure through a Java-based GUI

### Features
- Extract required system information from .mdl file.

```xml
<?xml version="1.0" encoding="utf-8"?>
<System>
  ...
  <Block BlockType="Constant" Name="Constant" SID="5">
    <P Name="Position">[780, 200, 810, 230]</P>
    <P Name="ZOrder">5</P>
  </Block>
  ...
  <Line>
    <P Name="ZOrder">2</P>
    <P Name="Src">1#out:1</P>
    <P Name="Points">[44, 0]</P>
    <Branch>
      <P Name="ZOrder">15</P>
      <P Name="Points">[0, 10]</P>
      <P Name="Dst">3#in:2</P>
    </Branch>
    <Branch>
      <P Name="ZOrder">14</P>
      <P Name="Dst">3#in:1</P>
    </Branch>
  </Line>
...
</System>
```
  
- parse the extracted information into four main classes: System, Blocks, Lines and Branches.
- Display the hierarchical structure.

  ![Example](https://github.com/Abdulrahman295/Simulink/assets/89452130/ed2f932d-23c0-4371-913a-ab51d9c527b9)
  
### Prerequisites
- Java Development Kit (JDK) 8 or above
- JavaFX SDK
