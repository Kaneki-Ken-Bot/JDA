/*
 *     Copyright 2015-2016 Austin Keener & Michael Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package net.dv8tion.jda.entities.impl;

import net.dv8tion.jda.entities.Emote;
import net.dv8tion.jda.entities.Guild;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EmoteImpl implements Emote
{
    private static final String EMOTE_URL_PREFIX = "https://discordcdn.com/emojis/";
    private final String id;
    private final String name;
    private List<Guild> guilds = new LinkedList<>();

    public EmoteImpl(String name, String id)
    {
        this.id = id;
        this.name = name;
    }

    public EmoteImpl addGuild(Guild... guilds)
    {
        for (Guild g : guilds)
        {
            if (!this.guilds.contains(g)) this.guilds.add(g);
        }
        return this;
    }

    public EmoteImpl removeGuild(Guild guild)
    {
        if (this.guilds.contains(guild)) this.guilds.remove(guild);
        return this;
    }

    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public boolean isAvailable()
    {
        return !guilds.isEmpty();
    }

    @Override
    public List<Guild> getGuilds()
    {
        return Collections.unmodifiableList(guilds);
    }

    @Override
    public String getImageUrl()
    {
        return EMOTE_URL_PREFIX + getId() + ".png";
    }

    @Override
    public String getAsEmote()
    {
        return "<:" + getName() + ":" + getId() + ">";
    }

    @Override
    public String toString()
    {
        return "E:" + (isAvailable() ? getAsEmote() : getName()) + "(" + getId() + ")";
    }

}
