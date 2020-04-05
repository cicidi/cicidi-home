# Source code of  [http://watlerchen.us](http://walter-chen.site)


## My Resume is nothing but a [resume.xml](https://github.com/cicidi/cicidi-home/blob/master/src/main/resources/resume_config/resume.xml)
```xml
           <bulletList>
                <bullet>
                    <content>Use Golang to develop EV charging and vehicle logging APIs flying in the Kubernetes Cloud.
                    </content>
                    <bulletList>
                        <bullet>
                            <content>Lead, architech design, implement Charging cloud API, which communite with Cell phone, Vehicle and charging provider's API using Golang
                            </content>
                        </bullet>
                        <bullet>
                            <content>Implement search engine and data store using Elastic search and Cassandra
                            </content>
                        </bullet>
                        <bullet>
                            <content>Deploy application to kubernete cluster use docker and Helm Chart.
                            </content>
                        </bullet>
                        <bullet>
                            <content>Upload vehicle logs and sensor data to Cloud data warehouse.
                            </content>
                        </bullet>
                    </bulletList>

                </bullet>
            </bulletList>
```
## Converted to PDF by Apache FOP from[ resume.schema](https://github.com/cicidi/cicidi-home/blob/master/src/main/resources/resume_config/resume-xsl-fo.xsl)
```xml
                   <fo:block font-family="sans-serif" space-before="0.5em" color="#666666">
                        <xsl:for-each select="bulletList/bullet">
                            <fo:block font-family="sans-serif" margin-left="3em">
                                <xsl:text>•</xsl:text>
                                <!--<xsl:text></xsl:text>-->
                                <xsl:value-of select="content"/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
```
## All my profile has been saved to mysql database  [domain model](https://github.com/cicidi/cicidi-home/blob/master/src/main/java/com/cicidi/home/domain/resume/WorkExperience.java)

## Then display as what you seen  [Front-end ](https://github.com/cicidi/cicidi-home/blob/master/src/main/resources/templates/profile.html)page

## And the site has
- **Gooogle Map API** to search and display all my job location
- **GitHub API**  to show my updates on portal
- **LinkedIn Api** to allow user login via Linked account.  **DISABLED** because of legal concern
- **WebCrawler** to grab vistor linkedin profile (only public info). **DISABLED** because of legal concern

## Others 


- I start my project by using a seed project from this link  <https://github.com/kolorobot/spring-boot-thymeleaf>
- Front end template get from this link  <http://www.free-css.com/free-css-templates>
- The site is only display my resume but I designed almost everything of a large site with Backend service, Security, Database, etc. 
- feel free to use my code without any license.
