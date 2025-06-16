package com.destroystokyo.paper;

import com.destroystokyo.paper.util.VersionFetcher;
import io.papermc.paper.ServerBuildInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.DefaultQualifier;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.TextColor.color;

@DefaultQualifier(NonNull.class)
public class PaperVersionFetcher implements VersionFetcher {

    @Override
    public long getCacheTime() {
        return 720000;
    }

    @Override
    public Component getVersionMessage(final String serverVersion) {
        final Component updateMessage;
        final ServerBuildInfo build = ServerBuildInfo.buildInfo();
        if (build.buildNumber().isEmpty() && build.gitCommit().isEmpty()) {
            updateMessage = text("You are running a development version without access to version information", color(0xFF5300));
        } else {
            updateMessage = getUpdateStatusMessage();
        }
        final @Nullable Component history = this.getHistory();

        return Component.textOfChildren(updateMessage, Component.newline(), history);
    }

    private static Component getUpdateStatusMessage() {
        return text("You are running the latest version", NamedTextColor.GREEN);
    }

    private Component getHistory() {
        return text("Previous version:", NamedTextColor.GRAY, TextDecoration.ITALIC);
    }
}
