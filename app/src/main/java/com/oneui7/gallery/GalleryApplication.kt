package com.oneui7.gallery

import android.app.Application
import android.graphics.Bitmap
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.VideoFrameDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache

class GalleryApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(VideoFrameDecoder.Factory())
            }
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.30)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("fhd_gallery_cache"))
                    .maxSizeBytes(250L * 1024 * 1024)
                    .build()
            }
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .allowHardware(true)
            .crossfade(150)
            .build()
    }
}
