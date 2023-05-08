<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ms="urn:schemas-microsoft-com:xslt">
    <xsl:template match="/battles">
         <html>
            <head>
                <meta charset="UTF-8"/>
                <link rel="stylesheet" href="../html/battle.css"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Battles</title>
            </head>
            <body>
                <xsl:element name="div">
                    <xsl:attribute name="class">all</xsl:attribute>
                    <xsl:element name="div">
                        <xsl:attribute name="class">header</xsl:attribute>
                        <xsl:element name="h1">Battles</xsl:element>
                    </xsl:element>
                    <xsl:element name="div">
                        <xsl:attribute name="class">battles</xsl:attribute>
                        <xsl:for-each select="battle">
                        <xsl:element name="div">
                            <xsl:attribute name="class">battle</xsl:attribute>
                            <xsl:element name="h1">Battle <xsl:value-of select="@id"/></xsl:element>
                            <xsl:element name="table">
                                <xsl:attribute name="class">battle_data</xsl:attribute>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Player ID</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="player_id"/></xsl:element>
                                </xsl:element>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Warrior ID</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="warrior_id"/></xsl:element>
                                </xsl:element>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Warrior weapon ID</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="warrior_weapon_id"/></xsl:element>
                                </xsl:element>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Opponent ID</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="opponent_id"/></xsl:element>
                                </xsl:element>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Opponent weapon ID</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="opponent_weapon_id"/></xsl:element>
                                </xsl:element>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Injuries caused</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="injuries_caused"/></xsl:element>
                                </xsl:element>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Injuries suffered</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="injuries_suffered"/></xsl:element>
                                </xsl:element>
                                <xsl:element name="tr">
                                    <xsl:element name="td">Battle points</xsl:element>
                                    <xsl:element name="td"><xsl:value-of select="battle_points"/></xsl:element>
                                </xsl:element>
                            </xsl:element>
                        </xsl:element>
                    </xsl:for-each>
                    </xsl:element>
                </xsl:element>
                 <xsl:element name="footer">
                    <xsl:element name="p">© Izan Villaécija, Joel Martin and Brian </xsl:element>
                </xsl:element>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>