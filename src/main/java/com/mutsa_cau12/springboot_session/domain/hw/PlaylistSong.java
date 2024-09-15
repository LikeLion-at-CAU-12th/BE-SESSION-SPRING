package com.mutsa_cau12.springboot_session.domain.hw;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "playlist_song_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "song_id")
    private Song song;
}
