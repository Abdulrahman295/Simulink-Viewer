## Simulink-Viewer
A software tool that reads Simulink MDL files and presents their hierarchical structure through a Java-based GUI

### Features
- Extract required system information from .mdl file.

```xml
<?xml version="1.0" encoding="utf-8"?>
<System>
  ...
  <Block BlockType="Sum" Name="Add" SID="3" >
    <P Name="Ports">[3, 1]</P>
    <P Name="Position">[1040, 209, 1070, 241]</P>
    <P Name="ZOrder">3</P>
    <P Name="IconShape">rectangular</P>
    <P Name="Inputs">+++</P>
  </Block>
  <Block BlockType="Constant" Name="Constant" SID="5">
    <P Name="Position">[780, 200, 810, 230]</P>
    <P Name="ZOrder">5</P>
  </Block>
  <Block BlockType="Saturate" Name="Saturation" SID="1">
    <P Name="Position">[935, 200, 965, 230]</P>
    <P Name="ZOrder">1</P>
  </Block>
  <Block BlockType="Scope" Name="Scope" SID="7">
    <P Name="Ports">[1]</P>
    <P Name="Position">[1130, 209, 1160, 241]</P>
    <P Name="ZOrder">7</P>
    <P Name="ScopeSpecificationString"></P>
    <P Name="NumInputPorts">1</P>
    <P Name="Floating">off</P>
  </Block>
  <Block BlockType="UnitDelay" Name="Unit Delay" SID="4">
    <P Name="Position">[1040, 283, 1075, 317]</P>
    <P Name="ZOrder">4</P>
    <P Name="BlockMirror">on</P>
    <P Name="SampleTime">-1</P>
    <P Name="HasFrameUpgradeWarning">on</P>
  </Block>
  <Line>
    <P Name="ZOrder">18</P>
    <P Name="Src">5#out:1</P>
    <P Name="Dst">1#in:1</P>
  </Line>
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
  <Line>
    <P Name="ZOrder">3</P>
    <P Name="Src">3#out:1</P>
    <P Name="Points">[40, 0]</P>
    <Branch>
      <P Name="ZOrder">13</P>
      <P Name="Points">[0, 75]</P>
      <P Name="Dst">4#in:1</P>
    </Branch>
    <Branch>
      <P Name="ZOrder">10</P>
      <P Name="Dst">7#in:1</P>
    </Branch>
  </Line>
  <Line>
    <P Name="ZOrder">4</P>
    <P Name="Src">4#out:1</P>
    <P Name="Points">[-8, 0; 0, -65]</P>
    <P Name="Dst">3#in:3</P>
  </Line>
</System>
```
  
- parse the extracted information into four main classes: System, Blocks, Lines and Branches.
- Display the hierarchical structure.

  ![Example](https://github.com/Abdulrahman295/Simulink/assets/89452130/ed2f932d-23c0-4371-913a-ab51d9c527b9)
  
### Prerequisites
- Java Development Kit (JDK) 8 or above
- JavaFX SDK
