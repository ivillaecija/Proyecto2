from lxml import etree
import os

def read_xml(path):
   file = open(path, 'r', encoding='utf-8')
   string = file.read()
   file.close()
   return bytes(bytearray(string, encoding='utf-8'))

def write_html(path, html):
   file = open(path, 'w', encoding='utf-8')
   file.write(html)
   file.close()

def transform(xmlTree):
   xslbattle = read_xml('./xml/template.xsl')
   xslTreebattle = etree.XML(xslbattle)
   transform = etree.XSLT(xslTreebattle)
   htmlDom = transform(xmlTree)
   htmlResult = etree.tostring(htmlDom, pretty_print=True).decode('utf-8')
   write_html("./html/battle.html", htmlResult)


xml = read_xml('./xml/battle.xml')
xmlTree = etree.XML(xml)

transform(xmlTree)